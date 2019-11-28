import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IPdsEnrollment, PdsEnrollment } from 'app/shared/model/pds-enrollment.model';
import { IPdsSchedule } from 'app/shared/model/pds-schedule.model';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { Observable, Subscription } from 'rxjs';
import { PdsEnrollmentService } from '../pds-enrollment/pds-enrollment.service';
import { PdsScheduleService } from './pds-schedule.service';



@Component({
  selector: 'jhi-pds-schedule',
  templateUrl: './pds-schedule.component.html'
})
export class PdsScheduleComponent implements OnInit, OnDestroy {
  pdsSchedules: IPdsSchedule[];
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
  isSaving: boolean;
  constructor(
    protected pdsScheduleService: PdsScheduleService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    private pdsEnrollmentService:PdsEnrollmentService
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
    this.pdsScheduleService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IPdsSchedule[]>) => this.paginatePdsSchedules(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/pds-schedule'], {
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
      '/pds-schedule',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInPdsSchedules();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPdsSchedule) {
    return item.id;
  }

  registerChangeInPdsSchedules() {
    this.eventSubscriber = this.eventManager.subscribe('pdsScheduleListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginatePdsSchedules(data: IPdsSchedule[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.pdsSchedules = data;
  }

  navToPdsEnrollment(pdsScheduleId:String){
    this.router.navigate(['pds-enrollment/new'],{
      queryParams:{
        'pds_schedule_id':pdsScheduleId
      }
    })
  }

  enrollToPds(pdsScheduleId:string){
    let pdsEnrollment=new PdsEnrollment();
    pdsEnrollment.enrolledToId=pdsScheduleId;
    pdsEnrollment.enrolledById=AccountService.getUserId();
    this.subscribeToSaveResponse(this.pdsEnrollmentService.create(pdsEnrollment));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPdsEnrollment>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
   // this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  previousState() {
    window.history.back();
  }
}
