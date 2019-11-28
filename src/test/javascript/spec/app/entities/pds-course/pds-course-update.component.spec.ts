import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsCourseUpdateComponent } from 'app/entities/pds-course/pds-course-update.component';
import { PdsCourseService } from 'app/entities/pds-course/pds-course.service';
import { PdsCourse } from 'app/shared/model/pds-course.model';

describe('Component Tests', () => {
  describe('PdsCourse Management Update Component', () => {
    let comp: PdsCourseUpdateComponent;
    let fixture: ComponentFixture<PdsCourseUpdateComponent>;
    let service: PdsCourseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsCourseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PdsCourseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PdsCourseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdsCourseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PdsCourse('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PdsCourse();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
