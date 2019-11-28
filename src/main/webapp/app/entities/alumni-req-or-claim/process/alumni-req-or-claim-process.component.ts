import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IAlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { Subscription } from 'rxjs';
import { AlumniReqOrClaimService } from '../alumni-req-or-claim.service';



@Component({
  selector: 'jhi-alumni-req-or-claim',
  templateUrl: './alumni-req-or-claim-process.component.html'
})
export class AlumniReqOrClaimProcessComponent implements OnInit, OnDestroy {
  alumniReqOrClaims: IAlumniReqOrClaim[];
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
  assigneeId:any

  constructor(
    protected alumniReqOrClaimService: AlumniReqOrClaimService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
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
    this.alumniReqOrClaimService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
        'assignee':AccountService.getUserId()
      })
      .subscribe((res: HttpResponse<IAlumniReqOrClaim[]>) => this.paginateAlumniReqOrClaims(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/alumni-req-or-claim/process'], {
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
      '/alumni-req-or-claim/process',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInAlumniReqOrClaims();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAlumniReqOrClaim) {
    return item.id;
  }

  registerChangeInAlumniReqOrClaims() {
    this.eventSubscriber = this.eventManager.subscribe('alumniReqOrClaimListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAlumniReqOrClaims(data: IAlumniReqOrClaim[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.alumniReqOrClaims = data;
  }
}
