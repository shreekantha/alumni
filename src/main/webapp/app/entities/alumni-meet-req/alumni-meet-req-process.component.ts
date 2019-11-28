import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AlumniMeetReq, IAlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';
import { AlumniMeetReqStatus } from 'app/shared/model/enumerations/alumni-meet-req-status.model';
import { JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { Observable, Subscription } from 'rxjs';
import { AlumniMeetReqService } from './alumni-meet-req.service';



@Component({
  selector: 'jhi-alumni-meet-req',
  templateUrl: './alumni-meet-req-process.component.html'
})
export class AlumniMeetReqProcessComponent implements OnInit, OnDestroy {
  alumniMeetReqs: IAlumniMeetReq[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected alumniMeetReqService: AlumniMeetReqService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected activatedRoute: ActivatedRoute,
    protected dataUtils: JhiDataUtils,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.alumniMeetReqService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
        'requested-to': AccountService.getUserId()

      })
      .subscribe((res: HttpResponse<IAlumniMeetReq[]>) => this.paginateAlumniMeetReqs(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/alumni-meet-req'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/alumni-meet-req',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInAlumniMeetReqs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAlumniMeetReq) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInAlumniMeetReqs() {
    this.eventSubscriber = this.eventManager.subscribe('alumniMeetReqListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAlumniMeetReqs(data: IAlumniMeetReq[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.alumniMeetReqs = data;
  }

  previousState() {
    window.history.back();
  }

  accept(alumniMeetReq: AlumniMeetReq) {

    alumniMeetReq.status = AlumniMeetReqStatus.ACCEPTED;
    this.subscribeToSaveResponse(this.alumniMeetReqService.update(alumniMeetReq));

  }

  reject(alumniMeetReq: AlumniMeetReq) {

    alumniMeetReq.status = AlumniMeetReqStatus.REJECTED;
    this.subscribeToSaveResponse(this.alumniMeetReqService.update(alumniMeetReq));

  }


  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlumniMeetReq>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
   // this.previousState();
  }

  protected onSaveError() {
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
