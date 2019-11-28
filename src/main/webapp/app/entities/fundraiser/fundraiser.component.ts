import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IFundraiser } from 'app/shared/model/fundraiser.model';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { Subscription } from 'rxjs';
import { FundraiserService } from './fundraiser.service';



@Component({
  selector: 'jhi-fundraiser',
  templateUrl: './fundraiser.component.html'
})
export class FundraiserComponent implements OnInit, OnDestroy {
  fundraisers: IFundraiser[];
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
    protected fundraiserService: FundraiserService,
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
    this.fundraiserService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IFundraiser[]>) => this.paginateFundraisers(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/fundraiser'], {
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
      '/fundraiser',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInFundraisers();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFundraiser) {
    return item.id;
  }

  registerChangeInFundraisers() {
    this.eventSubscriber = this.eventManager.subscribe('fundraiserListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateFundraisers(data: IFundraiser[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.fundraisers = data;
  }

  navToFundContribution(fundraiser:string){
    this.router.navigate(['/fund-contribution/new'],{
      queryParams:{'fundraiser-id':fundraiser}
    })
  }

  navToFundContributors(fundraiser:string){
    this.router.navigate(['/fund-contribution/contributors'],{
      queryParams:{'fundraiser-id':fundraiser}
    })
  }
}
