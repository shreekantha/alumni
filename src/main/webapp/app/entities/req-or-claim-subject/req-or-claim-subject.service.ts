import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';

type EntityResponseType = HttpResponse<IReqOrClaimSubject>;
type EntityArrayResponseType = HttpResponse<IReqOrClaimSubject[]>;

@Injectable({ providedIn: 'root' })
export class ReqOrClaimSubjectService {
  public resourceUrl = SERVER_API_URL + 'api/req-or-claim-subjects';

  constructor(protected http: HttpClient) {}

  create(reqOrClaimSubject: IReqOrClaimSubject): Observable<EntityResponseType> {
    return this.http.post<IReqOrClaimSubject>(this.resourceUrl, reqOrClaimSubject, { observe: 'response' });
  }

  update(reqOrClaimSubject: IReqOrClaimSubject): Observable<EntityResponseType> {
    return this.http.put<IReqOrClaimSubject>(this.resourceUrl, reqOrClaimSubject, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IReqOrClaimSubject>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReqOrClaimSubject[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
