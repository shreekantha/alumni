import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { AlumniMeetReqDeleteDialogComponent } from 'app/entities/alumni-meet-req/alumni-meet-req-delete-dialog.component';
import { AlumniMeetReqService } from 'app/entities/alumni-meet-req/alumni-meet-req.service';

describe('Component Tests', () => {
  describe('AlumniMeetReq Management Delete Component', () => {
    let comp: AlumniMeetReqDeleteDialogComponent;
    let fixture: ComponentFixture<AlumniMeetReqDeleteDialogComponent>;
    let service: AlumniMeetReqService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AlumniMeetReqDeleteDialogComponent]
      })
        .overrideTemplate(AlumniMeetReqDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlumniMeetReqDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlumniMeetReqService);
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
