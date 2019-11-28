import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFundraiser } from 'app/shared/model/fundraiser.model';

type EntityResponseType = HttpResponse<IFundraiser>;
type EntityArrayResponseType = HttpResponse<IFundraiser[]>;

@Injectable({ providedIn: 'root' })
export class FundraiserService {
  public resourceUrl = SERVER_API_URL + 'api/fundraisers';

  constructor(protected http: HttpClient) {}

  create(fundraiser: IFundraiser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fundraiser);
    return this.http
      .post<IFundraiser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fundraiser: IFundraiser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fundraiser);
    return this.http
      .put<IFundraiser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IFundraiser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFundraiser[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fundraiser: IFundraiser): IFundraiser {
    const copy: IFundraiser = Object.assign({}, fundraiser, {
      lastDateToContribute:
        fundraiser.lastDateToContribute != null && fundraiser.lastDateToContribute.isValid()
          ? fundraiser.lastDateToContribute.format(DATE_FORMAT)
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lastDateToContribute = res.body.lastDateToContribute != null ? moment(res.body.lastDateToContribute) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fundraiser: IFundraiser) => {
        fundraiser.lastDateToContribute = fundraiser.lastDateToContribute != null ? moment(fundraiser.lastDateToContribute) : null;
      });
    }
    return res;
  }
}
