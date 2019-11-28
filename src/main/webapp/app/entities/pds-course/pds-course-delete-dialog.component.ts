import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPdsCourse } from 'app/shared/model/pds-course.model';
import { PdsCourseService } from './pds-course.service';

@Component({
  selector: 'jhi-pds-course-delete-dialog',
  templateUrl: './pds-course-delete-dialog.component.html'
})
export class PdsCourseDeleteDialogComponent {
  pdsCourse: IPdsCourse;

  constructor(protected pdsCourseService: PdsCourseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.pdsCourseService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'pdsCourseListModification',
        content: 'Deleted an pdsCourse'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pds-course-delete-popup',
  template: ''
})
export class PdsCourseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pdsCourse }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PdsCourseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.pdsCourse = pdsCourse;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pds-course', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pds-course', { outlets: { popup: null } }]);
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
