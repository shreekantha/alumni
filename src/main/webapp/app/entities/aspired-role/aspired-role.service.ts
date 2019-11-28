import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAspiredRole } from 'app/shared/model/aspired-role.model';

type EntityResponseType = HttpResponse<IAspiredRole>;
type EntityArrayResponseType = HttpResponse<IAspiredRole[]>;

@Injectable({ providedIn: 'root' })
export class AspiredRoleService {
  public resourceUrl = SERVER_API_URL + 'api/aspired-roles';

  constructor(protected http: HttpClient) {}

  create(aspiredRole: IAspiredRole): Observable<EntityResponseType> {
    return this.http.post<IAspiredRole>(this.resourceUrl, aspiredRole, { observe: 'response' });
  }

  update(aspiredRole: IAspiredRole): Observable<EntityResponseType> {
    return this.http.put<IAspiredRole>(this.resourceUrl, aspiredRole, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAspiredRole>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAspiredRole[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
