import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IPdsCourseTopic, PdsCourseTopic } from 'app/shared/model/pds-course-topic.model';
import { PdsCourseTopicService } from './pds-course-topic.service';
import { IPdsCourse } from 'app/shared/model/pds-course.model';
import { PdsCourseService } from 'app/entities/pds-course/pds-course.service';

@Component({
  selector: 'jhi-pds-course-topic-update',
  templateUrl: './pds-course-topic-update.component.html'
})
export class PdsCourseTopicUpdateComponent implements OnInit {
  isSaving: boolean;

  pdscourses: IPdsCourse[];

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    duration: [],
    pdsCourseId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected pdsCourseTopicService: PdsCourseTopicService,
    protected pdsCourseService: PdsCourseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ pdsCourseTopic }) => {
      this.updateForm(pdsCourseTopic);
    });
    this.pdsCourseService
      .query()
      .subscribe((res: HttpResponse<IPdsCourse[]>) => (this.pdscourses = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(pdsCourseTopic: IPdsCourseTopic) {
    this.editForm.patchValue({
      id: pdsCourseTopic.id,
      name: pdsCourseTopic.name,
      description: pdsCourseTopic.description,
      duration: pdsCourseTopic.duration,
      pdsCourseId: pdsCourseTopic.pdsCourseId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const pdsCourseTopic = this.createFromForm();
    if (pdsCourseTopic.id !== undefined) {
      this.subscribeToSaveResponse(this.pdsCourseTopicService.update(pdsCourseTopic));
    } else {
      this.subscribeToSaveResponse(this.pdsCourseTopicService.create(pdsCourseTopic));
    }
  }

  private createFromForm(): IPdsCourseTopic {
    return {
      ...new PdsCourseTopic(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      duration: this.editForm.get(['duration']).value,
      pdsCourseId: this.editForm.get(['pdsCourseId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPdsCourseTopic>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackPdsCourseById(index: number, item: IPdsCourse) {
    return item.id;
  }
}
