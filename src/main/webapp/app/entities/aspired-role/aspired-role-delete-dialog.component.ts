import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAspiredRole } from 'app/shared/model/aspired-role.model';
import { AspiredRoleService } from './aspired-role.service';

@Component({
  selector: 'jhi-aspired-role-delete-dialog',
  templateUrl: './aspired-role-delete-dialog.component.html'
})
export class AspiredRoleDeleteDialogComponent {
  aspiredRole: IAspiredRole;

  constructor(
    protected aspiredRoleService: AspiredRoleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.aspiredRoleService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'aspiredRoleListModification',
        content: 'Deleted an aspiredRole'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-aspired-role-delete-popup',
  template: ''
})
export class AspiredRoleDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ aspiredRole }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AspiredRoleDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.aspiredRole = aspiredRole;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/aspired-role', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/aspired-role', { outlets: { popup: null } }]);
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
