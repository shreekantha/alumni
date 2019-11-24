import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { DhiAlumniCoreModule } from 'app/core/core.module';
import { DhiAlumniAppRoutingModule } from './app-routing.module';
import { DhiAlumniHomeModule } from './home/home.module';
import { DhiAlumniEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    DhiAlumniSharedModule,
    DhiAlumniCoreModule,
    DhiAlumniHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    DhiAlumniEntityModule,
    DhiAlumniAppRoutingModule
  ],
  declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [JhiMainComponent]
})
export class DhiAlumniAppModule {}
