import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { ProfDevServiceComponent } from './prof-dev-service.component';
import { ProfDevServiceDetailComponent } from './prof-dev-service-detail.component';
import { ProfDevServiceUpdateComponent } from './prof-dev-service-update.component';
import { ProfDevServiceDeletePopupComponent, ProfDevServiceDeleteDialogComponent } from './prof-dev-service-delete-dialog.component';
import { profDevServiceRoute, profDevServicePopupRoute } from './prof-dev-service.route';

const ENTITY_STATES = [...profDevServiceRoute, ...profDevServicePopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProfDevServiceComponent,
    ProfDevServiceDetailComponent,
    ProfDevServiceUpdateComponent,
    ProfDevServiceDeleteDialogComponent,
    ProfDevServiceDeletePopupComponent
  ],
  entryComponents: [ProfDevServiceDeleteDialogComponent]
})
export class DhiAlumniProfDevServiceModule {}
