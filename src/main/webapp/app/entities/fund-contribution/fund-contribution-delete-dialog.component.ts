import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFundContribution } from 'app/shared/model/fund-contribution.model';
import { FundContributionService } from './fund-contribution.service';

@Component({
  selector: 'jhi-fund-contribution-delete-dialog',
  templateUrl: './fund-contribution-delete-dialog.component.html'
})
export class FundContributionDeleteDialogComponent {
  fundContribution: IFundContribution;

  constructor(
    protected fundContributionService: FundContributionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.fundContributionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'fundContributionListModification',
        content: 'Deleted an fundContribution'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fund-contribution-delete-popup',
  template: ''
})
export class FundContributionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fundContribution }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FundContributionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.fundContribution = fundContribution;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/fund-contribution', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/fund-contribution', { outlets: { popup: null } }]);
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
