import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { MeetReqTopicUpdateComponent } from 'app/entities/meet-req-topic/meet-req-topic-update.component';
import { MeetReqTopicService } from 'app/entities/meet-req-topic/meet-req-topic.service';
import { MeetReqTopic } from 'app/shared/model/meet-req-topic.model';

describe('Component Tests', () => {
  describe('MeetReqTopic Management Update Component', () => {
    let comp: MeetReqTopicUpdateComponent;
    let fixture: ComponentFixture<MeetReqTopicUpdateComponent>;
    let service: MeetReqTopicService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [MeetReqTopicUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MeetReqTopicUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MeetReqTopicUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MeetReqTopicService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MeetReqTopic('123');
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
        const entity = new MeetReqTopic();
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
