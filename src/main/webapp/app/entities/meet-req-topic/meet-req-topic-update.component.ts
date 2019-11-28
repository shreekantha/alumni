import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMeetReqTopic, MeetReqTopic } from 'app/shared/model/meet-req-topic.model';
import { MeetReqTopicService } from './meet-req-topic.service';

@Component({
  selector: 'jhi-meet-req-topic-update',
  templateUrl: './meet-req-topic-update.component.html'
})
export class MeetReqTopicUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: []
  });

  constructor(protected meetReqTopicService: MeetReqTopicService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ meetReqTopic }) => {
      this.updateForm(meetReqTopic);
    });
  }

  updateForm(meetReqTopic: IMeetReqTopic) {
    this.editForm.patchValue({
      id: meetReqTopic.id,
      name: meetReqTopic.name,
      description: meetReqTopic.description
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const meetReqTopic = this.createFromForm();
    if (meetReqTopic.id !== undefined) {
      this.subscribeToSaveResponse(this.meetReqTopicService.update(meetReqTopic));
    } else {
      this.subscribeToSaveResponse(this.meetReqTopicService.create(meetReqTopic));
    }
  }

  private createFromForm(): IMeetReqTopic {
    return {
      ...new MeetReqTopic(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMeetReqTopic>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
