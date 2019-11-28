import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAlumni } from 'app/shared/model/alumni.model';

type EntityResponseType = HttpResponse<IAlumni>;
type EntityArrayResponseType = HttpResponse<IAlumni[]>;

@Injectable({ providedIn: 'root' })
export class AlumniService {
  public resourceUrl = SERVER_API_URL + 'api/alumni';

  constructor(protected http: HttpClient) {}

  create(alumni: IAlumni): Observable<EntityResponseType> {
    return this.http.post<IAlumni>(this.resourceUrl, alumni, { observe: 'response' });
  }

  update(alumni: IAlumni): Observable<EntityResponseType> {
    return this.http.put<IAlumni>(this.resourceUrl, alumni, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAlumni>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAlumni[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
