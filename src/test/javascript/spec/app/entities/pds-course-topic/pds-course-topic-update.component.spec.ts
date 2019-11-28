import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsCourseTopicUpdateComponent } from 'app/entities/pds-course-topic/pds-course-topic-update.component';
import { PdsCourseTopicService } from 'app/entities/pds-course-topic/pds-course-topic.service';
import { PdsCourseTopic } from 'app/shared/model/pds-course-topic.model';

describe('Component Tests', () => {
  describe('PdsCourseTopic Management Update Component', () => {
    let comp: PdsCourseTopicUpdateComponent;
    let fixture: ComponentFixture<PdsCourseTopicUpdateComponent>;
    let service: PdsCourseTopicService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsCourseTopicUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PdsCourseTopicUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PdsCourseTopicUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdsCourseTopicService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PdsCourseTopic('123');
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
        const entity = new PdsCourseTopic();
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
