import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { MeetReqTopicDeleteDialogComponent } from 'app/entities/meet-req-topic/meet-req-topic-delete-dialog.component';
import { MeetReqTopicService } from 'app/entities/meet-req-topic/meet-req-topic.service';

describe('Component Tests', () => {
  describe('MeetReqTopic Management Delete Component', () => {
    let comp: MeetReqTopicDeleteDialogComponent;
    let fixture: ComponentFixture<MeetReqTopicDeleteDialogComponent>;
    let service: MeetReqTopicService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [MeetReqTopicDeleteDialogComponent]
      })
        .overrideTemplate(MeetReqTopicDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MeetReqTopicDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MeetReqTopicService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
