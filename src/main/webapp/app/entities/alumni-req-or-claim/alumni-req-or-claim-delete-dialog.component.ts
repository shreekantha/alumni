import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';
import { AlumniReqOrClaimService } from './alumni-req-or-claim.service';

@Component({
  selector: 'jhi-alumni-req-or-claim-delete-dialog',
  templateUrl: './alumni-req-or-claim-delete-dialog.component.html'
})
export class AlumniReqOrClaimDeleteDialogComponent {
  alumniReqOrClaim: IAlumniReqOrClaim;

  constructor(
    protected alumniReqOrClaimService: AlumniReqOrClaimService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.alumniReqOrClaimService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'alumniReqOrClaimListModification',
        content: 'Deleted an alumniReqOrClaim'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-alumni-req-or-claim-delete-popup',
  template: ''
})
export class AlumniReqOrClaimDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alumniReqOrClaim }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AlumniReqOrClaimDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.alumniReqOrClaim = alumniReqOrClaim;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/alumni-req-or-claim', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/alumni-req-or-claim', { outlets: { popup: null } }]);
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
