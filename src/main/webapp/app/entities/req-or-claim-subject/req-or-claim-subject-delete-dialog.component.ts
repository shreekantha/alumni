import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';
import { ReqOrClaimSubjectService } from './req-or-claim-subject.service';

@Component({
  selector: 'jhi-req-or-claim-subject-delete-dialog',
  templateUrl: './req-or-claim-subject-delete-dialog.component.html'
})
export class ReqOrClaimSubjectDeleteDialogComponent {
  reqOrClaimSubject: IReqOrClaimSubject;

  constructor(
    protected reqOrClaimSubjectService: ReqOrClaimSubjectService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.reqOrClaimSubjectService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'reqOrClaimSubjectListModification',
        content: 'Deleted an reqOrClaimSubject'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-req-or-claim-subject-delete-popup',
  template: ''
})
export class ReqOrClaimSubjectDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ reqOrClaimSubject }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ReqOrClaimSubjectDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.reqOrClaimSubject = reqOrClaimSubject;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/req-or-claim-subject', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/req-or-claim-subject', { outlets: { popup: null } }]);
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
