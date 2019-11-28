import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsCourseTopicDeleteDialogComponent } from 'app/entities/pds-course-topic/pds-course-topic-delete-dialog.component';
import { PdsCourseTopicService } from 'app/entities/pds-course-topic/pds-course-topic.service';

describe('Component Tests', () => {
  describe('PdsCourseTopic Management Delete Component', () => {
    let comp: PdsCourseTopicDeleteDialogComponent;
    let fixture: ComponentFixture<PdsCourseTopicDeleteDialogComponent>;
    let service: PdsCourseTopicService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsCourseTopicDeleteDialogComponent]
      })
        .overrideTemplate(PdsCourseTopicDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdsCourseTopicDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdsCourseTopicService);
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
