import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { AspiredRoleComponent } from './aspired-role.component';
import { AspiredRoleDetailComponent } from './aspired-role-detail.component';
import { AspiredRoleUpdateComponent } from './aspired-role-update.component';
import { AspiredRoleDeletePopupComponent, AspiredRoleDeleteDialogComponent } from './aspired-role-delete-dialog.component';
import { aspiredRoleRoute, aspiredRolePopupRoute } from './aspired-role.route';

const ENTITY_STATES = [...aspiredRoleRoute, ...aspiredRolePopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AspiredRoleComponent,
    AspiredRoleDetailComponent,
    AspiredRoleUpdateComponent,
    AspiredRoleDeleteDialogComponent,
    AspiredRoleDeletePopupComponent
  ],
  entryComponents: [AspiredRoleDeleteDialogComponent]
})
export class DhiAlumniAspiredRoleModule {}
