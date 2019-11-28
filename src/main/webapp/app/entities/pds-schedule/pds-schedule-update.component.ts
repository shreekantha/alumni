// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ProfDevServiceService } from 'app/entities/prof-dev-service/prof-dev-service.service';
import { IPdsSchedule, PdsSchedule } from 'app/shared/model/pds-schedule.model';
import { IProfDevService } from 'app/shared/model/prof-dev-service.model';
import { JhiAlertService } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { PdsScheduleService } from './pds-schedule.service';

@Component({
  selector: 'jhi-pds-schedule-update',
  templateUrl: './pds-schedule-update.component.html'
})
export class PdsScheduleUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  profdevservices: IProfDevService[];
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    duration: [null, [Validators.required]],
    date: [null, [Validators.required]],
    time: [null, [Validators.required]],
    venue: [null, [Validators.required]],
    remarks: [],
    users: [],
    profDevServiceId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected pdsScheduleService: PdsScheduleService,
    protected userService: UserService,
    protected profDevServiceService: ProfDevServiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ pdsSchedule }) => {
      this.updateForm(pdsSchedule);
    });
    this.userService
      .query({
        role:'ROLE_FACULTY'
      })
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.profDevServiceService
      .query()
      .subscribe(
        (res: HttpResponse<IProfDevService[]>) => (this.profdevservices = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(pdsSchedule: IPdsSchedule) {
    this.editForm.patchValue({
      id: pdsSchedule.id,
      duration: pdsSchedule.duration,
      date: pdsSchedule.date,
      time: pdsSchedule.time,
      venue: pdsSchedule.venue,
      remarks: pdsSchedule.remarks,
      users: pdsSchedule.users,
      profDevServiceId: pdsSchedule.profDevServiceId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const pdsSchedule = this.createFromForm();
    if (pdsSchedule.id !== undefined) {
      this.subscribeToSaveResponse(this.pdsScheduleService.update(pdsSchedule));
    } else {
      this.subscribeToSaveResponse(this.pdsScheduleService.create(pdsSchedule));
    }
  }

  private createFromForm(): IPdsSchedule {
    return {
      ...new PdsSchedule(),
      id: this.editForm.get(['id']).value,
      duration: this.editForm.get(['duration']).value,
      date: this.editForm.get(['date']).value,
      time: this.editForm.get(['time']).value ,
      venue: this.editForm.get(['venue']).value,
      remarks: this.editForm.get(['remarks']).value,
      users: this.editForm.get(['users']).value,
      profDevServiceId: this.editForm.get(['profDevServiceId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPdsSchedule>>) {
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

  trackProfDevServiceById(index: number, item: IProfDevService) {
    return item.id;
  }

  getSelected(selectedVals: any[], option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
