import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { FundContributionDeleteDialogComponent, FundContributionDeletePopupComponent } from './fund-contribution-delete-dialog.component';
import { FundContributionDetailComponent } from './fund-contribution-detail.component';
import { FundContributionPerFundraiserComponent } from './fund-contribution-per-fundraiser.component';
import { FundContributionUpdateComponent } from './fund-contribution-update.component';
import { FundContributionComponent } from './fund-contribution.component';
import { fundContributionPopupRoute, fundContributionRoute } from './fund-contribution.route';


const ENTITY_STATES = [...fundContributionRoute, ...fundContributionPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FundContributionComponent,
    FundContributionDetailComponent,
    FundContributionUpdateComponent,
    FundContributionDeleteDialogComponent,
    FundContributionDeletePopupComponent,
    FundContributionPerFundraiserComponent
  ],
  entryComponents: [FundContributionDeleteDialogComponent]
})
export class DhiAlumniFundContributionModule {}
