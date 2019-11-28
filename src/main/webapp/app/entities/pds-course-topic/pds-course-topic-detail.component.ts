import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPdsCourseTopic } from 'app/shared/model/pds-course-topic.model';

@Component({
  selector: 'jhi-pds-course-topic-detail',
  templateUrl: './pds-course-topic-detail.component.html'
})
export class PdsCourseTopicDetailComponent implements OnInit {
  pdsCourseTopic: IPdsCourseTopic;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pdsCourseTopic }) => {
      this.pdsCourseTopic = pdsCourseTopic;
    });
  }

  previousState() {
    window.history.back();
  }
}
