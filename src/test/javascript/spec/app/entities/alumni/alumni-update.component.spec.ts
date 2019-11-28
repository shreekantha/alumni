import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { AlumniUpdateComponent } from 'app/entities/alumni/alumni-update.component';
import { AlumniService } from 'app/entities/alumni/alumni.service';
import { Alumni } from 'app/shared/model/alumni.model';

describe('Component Tests', () => {
  describe('Alumni Management Update Component', () => {
    let comp: AlumniUpdateComponent;
    let fixture: ComponentFixture<AlumniUpdateComponent>;
    let service: AlumniService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AlumniUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AlumniUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlumniUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlumniService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Alumni('123');
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
        const entity = new Alumni();
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
