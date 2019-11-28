import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMeetReqTopic } from 'app/shared/model/meet-req-topic.model';
import { MeetReqTopicService } from './meet-req-topic.service';

@Component({
  selector: 'jhi-meet-req-topic-delete-dialog',
  templateUrl: './meet-req-topic-delete-dialog.component.html'
})
export class MeetReqTopicDeleteDialogComponent {
  meetReqTopic: IMeetReqTopic;

  constructor(
    protected meetReqTopicService: MeetReqTopicService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.meetReqTopicService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'meetReqTopicListModification',
        content: 'Deleted an meetReqTopic'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-meet-req-topic-delete-popup',
  template: ''
})
export class MeetReqTopicDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ meetReqTopic }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MeetReqTopicDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.meetReqTopic = meetReqTopic;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/meet-req-topic', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/meet-req-topic', { outlets: { popup: null } }]);
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
