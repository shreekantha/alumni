import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsEnrollmentDeleteDialogComponent } from 'app/entities/pds-enrollment/pds-enrollment-delete-dialog.component';
import { PdsEnrollmentService } from 'app/entities/pds-enrollment/pds-enrollment.service';

describe('Component Tests', () => {
  describe('PdsEnrollment Management Delete Component', () => {
    let comp: PdsEnrollmentDeleteDialogComponent;
    let fixture: ComponentFixture<PdsEnrollmentDeleteDialogComponent>;
    let service: PdsEnrollmentService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsEnrollmentDeleteDialogComponent]
      })
        .overrideTemplate(PdsEnrollmentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdsEnrollmentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PdsEnrollmentService);
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
