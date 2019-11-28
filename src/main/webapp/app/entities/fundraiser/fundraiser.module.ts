import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { FundraiserComponent } from './fundraiser.component';
import { FundraiserDetailComponent } from './fundraiser-detail.component';
import { FundraiserUpdateComponent } from './fundraiser-update.component';
import { FundraiserDeletePopupComponent, FundraiserDeleteDialogComponent } from './fundraiser-delete-dialog.component';
import { fundraiserRoute, fundraiserPopupRoute } from './fundraiser.route';

const ENTITY_STATES = [...fundraiserRoute, ...fundraiserPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FundraiserComponent,
    FundraiserDetailComponent,
    FundraiserUpdateComponent,
    FundraiserDeleteDialogComponent,
    FundraiserDeletePopupComponent
  ],
  entryComponents: [FundraiserDeleteDialogComponent]
})
export class DhiAlumniFundraiserModule {}
