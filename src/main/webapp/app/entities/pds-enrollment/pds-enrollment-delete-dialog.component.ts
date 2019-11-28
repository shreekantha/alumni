import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPdsEnrollment } from 'app/shared/model/pds-enrollment.model';
import { PdsEnrollmentService } from './pds-enrollment.service';

@Component({
  selector: 'jhi-pds-enrollment-delete-dialog',
  templateUrl: './pds-enrollment-delete-dialog.component.html'
})
export class PdsEnrollmentDeleteDialogComponent {
  pdsEnrollment: IPdsEnrollment;

  constructor(
    protected pdsEnrollmentService: PdsEnrollmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.pdsEnrollmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'pdsEnrollmentListModification',
        content: 'Deleted an pdsEnrollment'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pds-enrollment-delete-popup',
  template: ''
})
export class PdsEnrollmentDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pdsEnrollment }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PdsEnrollmentDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.pdsEnrollment = pdsEnrollment;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pds-enrollment', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pds-enrollment', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
