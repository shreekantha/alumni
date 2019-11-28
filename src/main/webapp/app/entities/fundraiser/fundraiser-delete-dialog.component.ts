import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFundraiser } from 'app/shared/model/fundraiser.model';
import { FundraiserService } from './fundraiser.service';

@Component({
  selector: 'jhi-fundraiser-delete-dialog',
  templateUrl: './fundraiser-delete-dialog.component.html'
})
export class FundraiserDeleteDialogComponent {
  fundraiser: IFundraiser;

  constructor(
    protected fundraiserService: FundraiserService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.fundraiserService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'fundraiserListModification',
        content: 'Deleted an fundraiser'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fundraiser-delete-popup',
  template: ''
})
export class FundraiserDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fundraiser }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FundraiserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.fundraiser = fundraiser;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/fundraiser', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/fundraiser', { outlets: { popup: null } }]);
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
