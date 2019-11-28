import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPdsCourseTopic } from 'app/shared/model/pds-course-topic.model';
import { PdsCourseTopicService } from './pds-course-topic.service';

@Component({
  selector: 'jhi-pds-course-topic-delete-dialog',
  templateUrl: './pds-course-topic-delete-dialog.component.html'
})
export class PdsCourseTopicDeleteDialogComponent {
  pdsCourseTopic: IPdsCourseTopic;

  constructor(
    protected pdsCourseTopicService: PdsCourseTopicService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.pdsCourseTopicService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'pdsCourseTopicListModification',
        content: 'Deleted an pdsCourseTopic'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pds-course-topic-delete-popup',
  template: ''
})
export class PdsCourseTopicDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pdsCourseTopic }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PdsCourseTopicDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.pdsCourseTopic = pdsCourseTopic;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pds-course-topic', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pds-course-topic', { outlets: { popup: null } }]);
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
