import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfDevService } from 'app/shared/model/prof-dev-service.model';
import { ProfDevServiceService } from './prof-dev-service.service';

@Component({
  selector: 'jhi-prof-dev-service-delete-dialog',
  templateUrl: './prof-dev-service-delete-dialog.component.html'
})
export class ProfDevServiceDeleteDialogComponent {
  profDevService: IProfDevService;

  constructor(
    protected profDevServiceService: ProfDevServiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.profDevServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'profDevServiceListModification',
        content: 'Deleted an profDevService'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-prof-dev-service-delete-popup',
  template: ''
})
export class ProfDevServiceDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ profDevService }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProfDevServiceDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.profDevService = profDevService;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/prof-dev-service', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/prof-dev-service', { outlets: { popup: null } }]);
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
