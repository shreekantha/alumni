import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPdsSchedule } from 'app/shared/model/pds-schedule.model';
import { PdsScheduleService } from './pds-schedule.service';

@Component({
  selector: 'jhi-pds-schedule-delete-dialog',
  templateUrl: './pds-schedule-delete-dialog.component.html'
})
export class PdsScheduleDeleteDialogComponent {
  pdsSchedule: IPdsSchedule;

  constructor(
    protected pdsScheduleService: PdsScheduleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.pdsScheduleService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'pdsScheduleListModification',
        content: 'Deleted an pdsSchedule'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pds-schedule-delete-popup',
  template: ''
})
export class PdsScheduleDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pdsSchedule }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PdsScheduleDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.pdsSchedule = pdsSchedule;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pds-schedule', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pds-schedule', { outlets: { popup: null } }]);
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
