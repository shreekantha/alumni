import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFundContribution } from 'app/shared/model/fund-contribution.model';

type EntityResponseType = HttpResponse<IFundContribution>;
type EntityArrayResponseType = HttpResponse<IFundContribution[]>;

@Injectable({ providedIn: 'root' })
export class FundContributionService {
  public resourceUrl = SERVER_API_URL + 'api/fund-contributions';

  constructor(protected http: HttpClient) {}

  create(fundContribution: IFundContribution): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fundContribution);
    return this.http
      .post<IFundContribution>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fundContribution: IFundContribution): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fundContribution);
    return this.http
      .put<IFundContribution>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IFundContribution>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFundContribution[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fundContribution: IFundContribution): IFundContribution {
    const copy: IFundContribution = Object.assign({}, fundContribution, {
      contrDate: fundContribution.contrDate != null && fundContribution.contrDate.isValid() ? fundContribution.contrDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.contrDate = res.body.contrDate != null ? moment(res.body.contrDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fundContribution: IFundContribution) => {
        fundContribution.contrDate = fundContribution.contrDate != null ? moment(fundContribution.contrDate) : null;
      });
    }
    return res;
  }
}
