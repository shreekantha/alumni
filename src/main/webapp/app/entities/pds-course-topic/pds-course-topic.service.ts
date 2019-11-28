import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPdsCourseTopic } from 'app/shared/model/pds-course-topic.model';

type EntityResponseType = HttpResponse<IPdsCourseTopic>;
type EntityArrayResponseType = HttpResponse<IPdsCourseTopic[]>;

@Injectable({ providedIn: 'root' })
export class PdsCourseTopicService {
  public resourceUrl = SERVER_API_URL + 'api/pds-course-topics';

  constructor(protected http: HttpClient) {}

  create(pdsCourseTopic: IPdsCourseTopic): Observable<EntityResponseType> {
    return this.http.post<IPdsCourseTopic>(this.resourceUrl, pdsCourseTopic, { observe: 'response' });
  }

  update(pdsCourseTopic: IPdsCourseTopic): Observable<EntityResponseType> {
    return this.http.put<IPdsCourseTopic>(this.resourceUrl, pdsCourseTopic, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IPdsCourseTopic>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPdsCourseTopic[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
