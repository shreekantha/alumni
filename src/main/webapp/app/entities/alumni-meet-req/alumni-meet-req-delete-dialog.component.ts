import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';
import { AlumniMeetReqService } from './alumni-meet-req.service';

@Component({
  selector: 'jhi-alumni-meet-req-delete-dialog',
  templateUrl: './alumni-meet-req-delete-dialog.component.html'
})
export class AlumniMeetReqDeleteDialogComponent {
  alumniMeetReq: IAlumniMeetReq;

  constructor(
    protected alumniMeetReqService: AlumniMeetReqService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.alumniMeetReqService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'alumniMeetReqListModification',
        content: 'Deleted an alumniMeetReq'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-alumni-meet-req-delete-popup',
  template: ''
})
export class AlumniMeetReqDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alumniMeetReq }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AlumniMeetReqDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.alumniMeetReq = alumniMeetReq;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/alumni-meet-req', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/alumni-meet-req', { outlets: { popup: null } }]);
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
