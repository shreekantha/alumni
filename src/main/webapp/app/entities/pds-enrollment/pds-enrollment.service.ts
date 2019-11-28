import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPdsEnrollment } from 'app/shared/model/pds-enrollment.model';

type EntityResponseType = HttpResponse<IPdsEnrollment>;
type EntityArrayResponseType = HttpResponse<IPdsEnrollment[]>;

@Injectable({ providedIn: 'root' })
export class PdsEnrollmentService {
  public resourceUrl = SERVER_API_URL + 'api/pds-enrollments';

  constructor(protected http: HttpClient) {}

  create(pdsEnrollment: IPdsEnrollment): Observable<EntityResponseType> {
    return this.http.post<IPdsEnrollment>(this.resourceUrl, pdsEnrollment, { observe: 'response' });
  }

  update(pdsEnrollment: IPdsEnrollment): Observable<EntityResponseType> {
    return this.http.put<IPdsEnrollment>(this.resourceUrl, pdsEnrollment, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IPdsEnrollment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPdsEnrollment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
