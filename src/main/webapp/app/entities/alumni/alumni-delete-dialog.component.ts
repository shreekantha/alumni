import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlumni } from 'app/shared/model/alumni.model';
import { AlumniService } from './alumni.service';

@Component({
  selector: 'jhi-alumni-delete-dialog',
  templateUrl: './alumni-delete-dialog.component.html'
})
export class AlumniDeleteDialogComponent {
  alumni: IAlumni;

  constructor(protected alumniService: AlumniService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.alumniService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'alumniListModification',
        content: 'Deleted an alumni'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-alumni-delete-popup',
  template: ''
})
export class AlumniDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alumni }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AlumniDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.alumni = alumni;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/alumni', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/alumni', { outlets: { popup: null } }]);
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
