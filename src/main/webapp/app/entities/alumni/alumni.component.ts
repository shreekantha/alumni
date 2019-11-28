import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IAlumni } from 'app/shared/model/alumni.model';
import { IAspiredRole } from 'app/shared/model/aspired-role.model';
import { Department } from 'app/shared/model/department.model';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { Subscription } from 'rxjs';
import { AspiredRoleService } from '../aspired-role/aspired-role.service';
import { DepartmentService } from '../department/department.service';
import { AlumniService } from './alumni.service';



@Component({
  selector: 'jhi-alumni',
  templateUrl: './alumni.component.html'
})
export class AlumniComponent implements OnInit, OnDestroy {
  alumni: IAlumni[];
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
  position: any;
  experience:any=1;
  yearOfGraduation:any='2018-19';
  department:any;
  jobPositions: IAspiredRole[];
  departments: Department[];
  experiences: any[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  graduationYears: any[] = ['2018-19','2017-18','2016-17','2015-16','2014-15','2013-14','2002-03','2001-02','2000-01',];

  constructor(
    protected alumniService: AlumniService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    private aspiredRoleService: AspiredRoleService,
    private departmentService: DepartmentService
  ) {



    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  getData(){
    console.log(this.department);
  //  if(this.department!=null && )
    this.loadAll();
  }
  loadAll() {
    this.alumniService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
        'experience':this.experience,
        'year-of-graduation':this.yearOfGraduation,
        'position':this.position,
        'department':this.department
      })
      .subscribe((res: HttpResponse<IAlumni[]>) => this.paginateAlumni(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/alumni'], {
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
      '/alumni',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.aspiredRoleService
      .query()
      .subscribe((res: HttpResponse<IAspiredRole[]>) => {
        this.jobPositions = res.body
        this.position=this.jobPositions[0].name;
      });
    this.departmentService
      .query()
      .subscribe((res: HttpResponse<IAspiredRole[]>) => {
        this.departments = res.body
        this.department=this.departments[0].shortName;
        this.loadAll();
      });
  //  this.loadAll();
 // this.getData();
    this.registerChangeInAlumni();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAlumni) {
    return item.id;
  }

  registerChangeInAlumni() {
    this.eventSubscriber = this.eventManager.subscribe('alumniListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAlumni(data: IAlumni[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.alumni = data;
  }
}
