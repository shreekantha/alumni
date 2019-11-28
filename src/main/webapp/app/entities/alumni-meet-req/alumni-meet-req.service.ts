import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';

type EntityResponseType = HttpResponse<IAlumniMeetReq>;
type EntityArrayResponseType = HttpResponse<IAlumniMeetReq[]>;

@Injectable({ providedIn: 'root' })
export class AlumniMeetReqService {
  public resourceUrl = SERVER_API_URL + 'api/alumni-meet-reqs';

  constructor(protected http: HttpClient) {}

  create(alumniMeetReq: IAlumniMeetReq): Observable<EntityResponseType> {
    return this.http.post<IAlumniMeetReq>(this.resourceUrl, alumniMeetReq, { observe: 'response' });
  }

  update(alumniMeetReq: IAlumniMeetReq): Observable<EntityResponseType> {
    return this.http.put<IAlumniMeetReq>(this.resourceUrl, alumniMeetReq, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAlumniMeetReq>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAlumniMeetReq[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
