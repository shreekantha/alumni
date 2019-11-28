import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IFundContribution } from 'app/shared/model/fund-contribution.model';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { Subscription } from 'rxjs';
import { FundContributionService } from './fund-contribution.service';



@Component({
  selector: 'jhi-fund-contribution-1',
  templateUrl: './fund-contribution-per-fundraiser.component.html'
})
export class FundContributionPerFundraiserComponent implements OnInit, OnDestroy {
  fundContributions: IFundContribution[];
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
  fundraiserId:any

  constructor(
    protected fundContributionService: FundContributionService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.fundraiserId = this.activatedRoute.snapshot.queryParams['fundraiser-id'];
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.fundContributionService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
        'fundraiser-id':this.fundraiserId
      })
      .subscribe((res: HttpResponse<IFundContribution[]>) => this.paginateFundContributions(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/fund-contribution'], {
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
      '/fund-contribution',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInFundContributions();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFundContribution) {
    return item.id;
  }

  registerChangeInFundContributions() {
    this.eventSubscriber = this.eventManager.subscribe('fundContributionListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateFundContributions(data: IFundContribution[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.fundContributions = data;
  }
}
