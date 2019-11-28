import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IPdsCourse, PdsCourse } from 'app/shared/model/pds-course.model';
import { PdsCourseService } from './pds-course.service';
import { IProfDevService } from 'app/shared/model/prof-dev-service.model';
import { ProfDevServiceService } from 'app/entities/prof-dev-service/prof-dev-service.service';

@Component({
  selector: 'jhi-pds-course-update',
  templateUrl: './pds-course-update.component.html'
})
export class PdsCourseUpdateComponent implements OnInit {
  isSaving: boolean;

  profdevservices: IProfDevService[];

  editForm = this.fb.group({
    id: [],
    courseName: [null, [Validators.required]],
    description: [],
    profDevServiceId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected pdsCourseService: PdsCourseService,
    protected profDevServiceService: ProfDevServiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ pdsCourse }) => {
      this.updateForm(pdsCourse);
    });
    this.profDevServiceService
      .query()
      .subscribe(
        (res: HttpResponse<IProfDevService[]>) => (this.profdevservices = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(pdsCourse: IPdsCourse) {
    this.editForm.patchValue({
      id: pdsCourse.id,
      courseName: pdsCourse.courseName,
      description: pdsCourse.description,
      profDevServiceId: pdsCourse.profDevServiceId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const pdsCourse = this.createFromForm();
    if (pdsCourse.id !== undefined) {
      this.subscribeToSaveResponse(this.pdsCourseService.update(pdsCourse));
    } else {
      this.subscribeToSaveResponse(this.pdsCourseService.create(pdsCourse));
    }
  }

  private createFromForm(): IPdsCourse {
    return {
      ...new PdsCourse(),
      id: this.editForm.get(['id']).value,
      courseName: this.editForm.get(['courseName']).value,
      description: this.editForm.get(['description']).value,
      profDevServiceId: this.editForm.get(['profDevServiceId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPdsCourse>>) {
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

  trackProfDevServiceById(index: number, item: IProfDevService) {
    return item.id;
  }
}
