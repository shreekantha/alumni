import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { ReqOrClaimSubjectDeleteDialogComponent } from 'app/entities/req-or-claim-subject/req-or-claim-subject-delete-dialog.component';
import { ReqOrClaimSubjectService } from 'app/entities/req-or-claim-subject/req-or-claim-subject.service';

describe('Component Tests', () => {
  describe('ReqOrClaimSubject Management Delete Component', () => {
    let comp: ReqOrClaimSubjectDeleteDialogComponent;
    let fixture: ComponentFixture<ReqOrClaimSubjectDeleteDialogComponent>;
    let service: ReqOrClaimSubjectService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [ReqOrClaimSubjectDeleteDialogComponent]
      })
        .overrideTemplate(ReqOrClaimSubjectDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReqOrClaimSubjectDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReqOrClaimSubjectService);
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
