import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IFundraiser, Fundraiser } from 'app/shared/model/fundraiser.model';
import { FundraiserService } from './fundraiser.service';

@Component({
  selector: 'jhi-fundraiser-update',
  templateUrl: './fundraiser-update.component.html'
})
export class FundraiserUpdateComponent implements OnInit {
  isSaving: boolean;
  lastDateToContributeDp: any;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [],
    targetAmount: [null, [Validators.required]],
    collectedAmount: [],
    lastDateToContribute: [],
    status: []
  });

  constructor(protected fundraiserService: FundraiserService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ fundraiser }) => {
      this.updateForm(fundraiser);
    });
  }

  updateForm(fundraiser: IFundraiser) {
    this.editForm.patchValue({
      id: fundraiser.id,
      name: fundraiser.name,
      description: fundraiser.description,
      targetAmount: fundraiser.targetAmount,
      collectedAmount: fundraiser.collectedAmount,
      lastDateToContribute: fundraiser.lastDateToContribute,
      status: fundraiser.status
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fundraiser = this.createFromForm();
    if (fundraiser.id !== undefined) {
      this.subscribeToSaveResponse(this.fundraiserService.update(fundraiser));
    } else {
      this.subscribeToSaveResponse(this.fundraiserService.create(fundraiser));
    }
  }

  private createFromForm(): IFundraiser {
    return {
      ...new Fundraiser(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      targetAmount: this.editForm.get(['targetAmount']).value,
      collectedAmount: this.editForm.get(['collectedAmount']).value,
      lastDateToContribute: this.editForm.get(['lastDateToContribute']).value,
      status: this.editForm.get(['status']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFundraiser>>) {
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
