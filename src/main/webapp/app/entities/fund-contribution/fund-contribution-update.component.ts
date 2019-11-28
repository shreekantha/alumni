// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { FundraiserService } from 'app/entities/fundraiser/fundraiser.service';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FundContribution, IFundContribution } from 'app/shared/model/fund-contribution.model';
import { IFundraiser } from 'app/shared/model/fundraiser.model';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { FundContributionService } from './fund-contribution.service';

@Component({
  selector: 'jhi-fund-contribution-update',
  templateUrl: './fund-contribution-update.component.html'
})
export class FundContributionUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  fundraisers: IFundraiser[];
  fundraiserId: string;

  editForm = this.fb.group({
    id: [],
    currency: [null, [Validators.required]],
    contrAmount: [null, [Validators.required]],
    contrDate: [],
    contibutorId: [null, Validators.required],
    fundraiserId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected fundContributionService: FundContributionService,
    protected userService: UserService,
    protected fundraiserService: FundraiserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.isSaving = false;
    this.fundraiserId = this.activatedRoute.snapshot.queryParams['fundraiser-id'];

    this.activatedRoute.data.subscribe(({ fundContribution }) => {
      console.error('fundraiser-id', this.fundraiserId);
      fundContribution.fundraiserId = this.fundraiserId;
      fundContribution.contibutorId=AccountService.getUserId();
      this.updateForm(fundContribution);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.fundraiserService
      .query()
      .subscribe(
        (res: HttpResponse<IFundraiser[]>) => (this.fundraisers = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(fundContribution: IFundContribution) {
    this.editForm.patchValue({
      id: fundContribution.id,
      currency: fundContribution.currency,
      contrAmount: fundContribution.contrAmount,
      contrDate: fundContribution.contrDate != null ? fundContribution.contrDate.format(DATE_TIME_FORMAT) : null,
      contibutorId: fundContribution.contibutorId,
      fundraiserId: fundContribution.fundraiserId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fundContribution = this.createFromForm();
    if (fundContribution.id !== undefined) {
      this.subscribeToSaveResponse(this.fundContributionService.update(fundContribution));
    } else {
      this.subscribeToSaveResponse(this.fundContributionService.create(fundContribution));
    }
  }

  private createFromForm(): IFundContribution {
    return {
      ...new FundContribution(),
      id: this.editForm.get(['id']).value,
      currency: this.editForm.get(['currency']).value,
      contrAmount: this.editForm.get(['contrAmount']).value,
      contrDate:
        this.editForm.get(['contrDate']).value != null ? moment(this.editForm.get(['contrDate']).value, DATE_TIME_FORMAT) : undefined,
      contibutorId: this.editForm.get(['contibutorId']).value,
      fundraiserId: this.editForm.get(['fundraiserId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFundContribution>>) {
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

  trackFundraiserById(index: number, item: IFundraiser) {
    return item.id;
  }
}
