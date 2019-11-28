import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IPdsEnrollment, PdsEnrollment } from 'app/shared/model/pds-enrollment.model';
import { PdsEnrollmentService } from './pds-enrollment.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IPdsSchedule } from 'app/shared/model/pds-schedule.model';
import { PdsScheduleService } from 'app/entities/pds-schedule/pds-schedule.service';

@Component({
  selector: 'jhi-pds-enrollment-update',
  templateUrl: './pds-enrollment-update.component.html'
})
export class PdsEnrollmentUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  pdsschedules: IPdsSchedule[];

  editForm = this.fb.group({
    id: [],
    remarks: [],
    enrolledById: [null, Validators.required],
    enrolledToId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected pdsEnrollmentService: PdsEnrollmentService,
    protected userService: UserService,
    protected pdsScheduleService: PdsScheduleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ pdsEnrollment }) => {
      this.updateForm(pdsEnrollment);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.pdsScheduleService
      .query()
      .subscribe(
        (res: HttpResponse<IPdsSchedule[]>) => (this.pdsschedules = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(pdsEnrollment: IPdsEnrollment) {
    this.editForm.patchValue({
      id: pdsEnrollment.id,
      remarks: pdsEnrollment.remarks,
      enrolledById: pdsEnrollment.enrolledById,
      enrolledToId: pdsEnrollment.enrolledToId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const pdsEnrollment = this.createFromForm();
    if (pdsEnrollment.id !== undefined) {
      this.subscribeToSaveResponse(this.pdsEnrollmentService.update(pdsEnrollment));
    } else {
      this.subscribeToSaveResponse(this.pdsEnrollmentService.create(pdsEnrollment));
    }
  }

  private createFromForm(): IPdsEnrollment {
    return {
      ...new PdsEnrollment(),
      id: this.editForm.get(['id']).value,
      remarks: this.editForm.get(['remarks']).value,
      enrolledById: this.editForm.get(['enrolledById']).value,
      enrolledToId: this.editForm.get(['enrolledToId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPdsEnrollment>>) {
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

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackPdsScheduleById(index: number, item: IPdsSchedule) {
    return item.id;
  }
}
