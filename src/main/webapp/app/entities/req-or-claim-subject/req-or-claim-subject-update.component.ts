import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IReqOrClaimSubject, ReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';
import { ReqOrClaimSubjectService } from './req-or-claim-subject.service';

@Component({
  selector: 'jhi-req-or-claim-subject-update',
  templateUrl: './req-or-claim-subject-update.component.html'
})
export class ReqOrClaimSubjectUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    decsription: []
  });

  constructor(
    protected reqOrClaimSubjectService: ReqOrClaimSubjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ reqOrClaimSubject }) => {
      this.updateForm(reqOrClaimSubject);
    });
  }

  updateForm(reqOrClaimSubject: IReqOrClaimSubject) {
    this.editForm.patchValue({
      id: reqOrClaimSubject.id,
      name: reqOrClaimSubject.name,
      decsription: reqOrClaimSubject.decsription
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const reqOrClaimSubject = this.createFromForm();
    if (reqOrClaimSubject.id !== undefined) {
      this.subscribeToSaveResponse(this.reqOrClaimSubjectService.update(reqOrClaimSubject));
    } else {
      this.subscribeToSaveResponse(this.reqOrClaimSubjectService.create(reqOrClaimSubject));
    }
  }

  private createFromForm(): IReqOrClaimSubject {
    return {
      ...new ReqOrClaimSubject(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      decsription: this.editForm.get(['decsription']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReqOrClaimSubject>>) {
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
