import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPdsCourse } from 'app/shared/model/pds-course.model';

type EntityResponseType = HttpResponse<IPdsCourse>;
type EntityArrayResponseType = HttpResponse<IPdsCourse[]>;

@Injectable({ providedIn: 'root' })
export class PdsCourseService {
  public resourceUrl = SERVER_API_URL + 'api/pds-courses';

  constructor(protected http: HttpClient) {}

  create(pdsCourse: IPdsCourse): Observable<EntityResponseType> {
    return this.http.post<IPdsCourse>(this.resourceUrl, pdsCourse, { observe: 'response' });
  }

  update(pdsCourse: IPdsCourse): Observable<EntityResponseType> {
    return this.http.put<IPdsCourse>(this.resourceUrl, pdsCourse, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IPdsCourse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPdsCourse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
