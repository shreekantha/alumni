// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ReqOrClaimSubjectService } from 'app/entities/req-or-claim-subject/req-or-claim-subject.service';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AlumniReqOrClaim, IAlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';
import { ReqOrClaimStatus } from 'app/shared/model/enumerations/req-or-claim-status.model';
import { IReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { AlumniReqOrClaimService } from './alumni-req-or-claim.service';

@Component({
  selector: 'jhi-alumni-req-or-claim-update',
  templateUrl: './alumni-req-or-claim-update.component.html'
})
export class AlumniReqOrClaimUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  reqorclaimsubjects: IReqOrClaimSubject[];

  editForm = this.fb.group({
    id: [],
    requestFor: [],
    reason: [],
    status: [],
    requestedDate: [],
    requestById: [],
    assigneeId: [null, Validators.required],
    subjectId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected alumniReqOrClaimService: AlumniReqOrClaimService,
    protected userService: UserService,
    protected reqOrClaimSubjectService: ReqOrClaimSubjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ alumniReqOrClaim }) => {
      this.updateForm(alumniReqOrClaim);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.reqOrClaimSubjectService
      .query()
      .subscribe(
        (res: HttpResponse<IReqOrClaimSubject[]>) => (this.reqorclaimsubjects = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(alumniReqOrClaim: IAlumniReqOrClaim) {
    alumniReqOrClaim.requestById = AccountService.getUserId();
    this.editForm.patchValue({
      id: alumniReqOrClaim.id,
      requestFor: alumniReqOrClaim.requestFor,
      reason: alumniReqOrClaim.reason,
      status: alumniReqOrClaim.status,
      requestedDate: alumniReqOrClaim.requestedDate != null ? alumniReqOrClaim.requestedDate.format(DATE_TIME_FORMAT) : null,
      requestById: alumniReqOrClaim.requestById,
      assigneeId: alumniReqOrClaim.assigneeId,
      subjectId: alumniReqOrClaim.subjectId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const alumniReqOrClaim = this.createFromForm();
    if (alumniReqOrClaim.id !== undefined) {
      this.subscribeToSaveResponse(this.alumniReqOrClaimService.update(alumniReqOrClaim));
    } else {
      alumniReqOrClaim.status=ReqOrClaimStatus.SUBMITTED;
      this.subscribeToSaveResponse(this.alumniReqOrClaimService.create(alumniReqOrClaim));
    }
  }

  private createFromForm(): IAlumniReqOrClaim {
    return {
      ...new AlumniReqOrClaim(),
      id: this.editForm.get(['id']).value,
      requestFor: this.editForm.get(['requestFor']).value,
      reason: this.editForm.get(['reason']).value,
      status: this.editForm.get(['status']).value,
      requestedDate:
        this.editForm.get(['requestedDate']).value != null
          ? moment(this.editForm.get(['requestedDate']).value, DATE_TIME_FORMAT)
          : undefined,
      requestById: this.editForm.get(['requestById']).value,
      assigneeId: this.editForm.get(['assigneeId']).value,
      subjectId: this.editForm.get(['subjectId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlumniReqOrClaim>>) {
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

  trackReqOrClaimSubjectById(index: number, item: IReqOrClaimSubject) {
    return item.id;
  }
}
