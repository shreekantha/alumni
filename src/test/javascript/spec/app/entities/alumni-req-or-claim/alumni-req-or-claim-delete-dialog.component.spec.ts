import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { AlumniReqOrClaimDeleteDialogComponent } from 'app/entities/alumni-req-or-claim/alumni-req-or-claim-delete-dialog.component';
import { AlumniReqOrClaimService } from 'app/entities/alumni-req-or-claim/alumni-req-or-claim.service';

describe('Component Tests', () => {
  describe('AlumniReqOrClaim Management Delete Component', () => {
    let comp: AlumniReqOrClaimDeleteDialogComponent;
    let fixture: ComponentFixture<AlumniReqOrClaimDeleteDialogComponent>;
    let service: AlumniReqOrClaimService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AlumniReqOrClaimDeleteDialogComponent]
      })
        .overrideTemplate(AlumniReqOrClaimDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlumniReqOrClaimDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlumniReqOrClaimService);
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
