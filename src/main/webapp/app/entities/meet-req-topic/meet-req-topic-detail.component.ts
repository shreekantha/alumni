import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMeetReqTopic } from 'app/shared/model/meet-req-topic.model';

@Component({
  selector: 'jhi-meet-req-topic-detail',
  templateUrl: './meet-req-topic-detail.component.html'
})
export class MeetReqTopicDetailComponent implements OnInit {
  meetReqTopic: IMeetReqTopic;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ meetReqTopic }) => {
      this.meetReqTopic = meetReqTopic;
    });
  }

  previousState() {
    window.history.back();
  }
}
