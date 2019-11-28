import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';

type EntityResponseType = HttpResponse<IAlumniReqOrClaim>;
type EntityArrayResponseType = HttpResponse<IAlumniReqOrClaim[]>;

@Injectable({ providedIn: 'root' })
export class AlumniReqOrClaimService {
  public resourceUrl = SERVER_API_URL + 'api/alumni-req-or-claims';

  constructor(protected http: HttpClient) {}

  create(alumniReqOrClaim: IAlumniReqOrClaim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alumniReqOrClaim);
    return this.http
      .post<IAlumniReqOrClaim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(alumniReqOrClaim: IAlumniReqOrClaim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alumniReqOrClaim);
    return this.http
      .put<IAlumniReqOrClaim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IAlumniReqOrClaim>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAlumniReqOrClaim[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(alumniReqOrClaim: IAlumniReqOrClaim): IAlumniReqOrClaim {
    const copy: IAlumniReqOrClaim = Object.assign({}, alumniReqOrClaim, {
      requestedDate:
        alumniReqOrClaim.requestedDate != null && alumniReqOrClaim.requestedDate.isValid() ? alumniReqOrClaim.requestedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.requestedDate = res.body.requestedDate != null ? moment(res.body.requestedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((alumniReqOrClaim: IAlumniReqOrClaim) => {
        alumniReqOrClaim.requestedDate = alumniReqOrClaim.requestedDate != null ? moment(alumniReqOrClaim.requestedDate) : null;
      });
    }
    return res;
  }
}
