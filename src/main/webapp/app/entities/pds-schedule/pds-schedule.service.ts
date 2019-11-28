import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { IPdsSchedule } from 'app/shared/model/pds-schedule.model';
import { createRequestOption } from 'app/shared/util/request-util';
import * as moment from 'moment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


type EntityResponseType = HttpResponse<IPdsSchedule>;
type EntityArrayResponseType = HttpResponse<IPdsSchedule[]>;

@Injectable({ providedIn: 'root' })
export class PdsScheduleService {
  public resourceUrl = SERVER_API_URL + 'api/pds-schedules';

  constructor(protected http: HttpClient) {}

  create(pdsSchedule: IPdsSchedule): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pdsSchedule);
    return this.http
      .post<IPdsSchedule>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(pdsSchedule: IPdsSchedule): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pdsSchedule);
    return this.http
      .put<IPdsSchedule>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IPdsSchedule>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPdsSchedule[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(pdsSchedule: IPdsSchedule): IPdsSchedule {
    const copy: IPdsSchedule = Object.assign({}, pdsSchedule, {
      date: pdsSchedule.date != null && pdsSchedule.date.isValid() ? pdsSchedule.date.format(DATE_FORMAT) : null,
    //  time: pdsSchedule.time != null && pdsSchedule.time.isValid() ? pdsSchedule.time.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date != null ? moment(res.body.date) : null;
     // res.body.time = res.body.time != null ? moment(res.body.time) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((pdsSchedule: IPdsSchedule) => {
        pdsSchedule.date = pdsSchedule.date != null ? moment(pdsSchedule.date) : null;
       // pdsSchedule.time = pdsSchedule.time != null ? moment(pdsSchedule.time) : null;
      });
    }
    return res;
  }
}
